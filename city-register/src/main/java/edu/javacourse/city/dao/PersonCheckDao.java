package edu.javacourse.city.dao;

import edu.javacourse.city.domain.PersonRequest;
import edu.javacourse.city.domain.PersonResponse;
import edu.javacourse.city.exception.PersonCheckException;

import java.sql.*;

public class PersonCheckDao
{
    private static final String SQL_REQUEST=
            " select temporal from cr_address_person ap" +
            " inner join cr_person p on p.person_id= ap.person_id" +
            " inner join cr_address a on a.address_id = ap.address_id" +
            " where" +
            " CURRENT_DATE > ap.start_date and (CURRENT_DATE <= ap.end_date or ap.end_date is NULL) " +
            " and upper(p.sur_name)=upper(?)" +
            " and upper(p.given_name) = upper(?)" +
            " and upper(p.patronymic) = upper(?)" +
    //   " and p.date_of_birth =?" +
            " and a.street_code=?" +
            " and upper(a.building) =upper(?) ";

    private ConnectionBuilder connectionBuilder;

    public void setConnectionBuilder(ConnectionBuilder connectionBuilder) {
        this.connectionBuilder = connectionBuilder;
    }

    private Connection getConnection() throws SQLException
    {
        return connectionBuilder.getConnection();
    }
    public PersonResponse checkPerson (PersonRequest request) throws PersonCheckException
  {
      PersonResponse response = new PersonResponse();

      String sql = SQL_REQUEST;
      if(request.getExtension() !=null)
      {
          sql+= " and upper(a.extension) = upper(?) ";
      }else {
          sql+= " and a.extension is NULL ";
      }
      if(request.getApartment()!= null)
      {
          sql+=" and upper(a.apartment)= upper(?) ";
      }
      else {
          sql+= " and a.apartment is NULL ";
      }

      try(Connection con= getConnection();
          PreparedStatement stmt= con.prepareStatement(sql))
      {

          Statement st= con.createStatement();
          st.executeUpdate("SET client_encoding TO 'UTF8';");
          st.close();

          int count =0;
          stmt.setString(++count, request.getSurName());
          stmt.setString(++count, request.getGivenName());
          stmt.setString(++count, request.getPatronymic());
        //  stmt.setDate(++count, java.sql.Date.valueOf(request.getDateOfBirth()));
          stmt.setInt(++count, request.getStreetCode());
          stmt.setString(++count, request.getBuilding());
          if(request.getExtension()!=null)
          stmt.setString(++count, request.getExtension());
          if (request.getApartment()!=null)
          stmt.setString(++count, request.getApartment());


          ResultSet rs = stmt.executeQuery();
          if(rs.next())
          {
              response.setRegistered(true);
              response.setTemporal(rs.getBoolean("temporal"));
          }

      }
      catch(SQLException ex)
      {
         throw new PersonCheckException(ex);
      }

      return response;
  }


}
