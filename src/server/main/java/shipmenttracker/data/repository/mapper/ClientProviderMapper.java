package shipmenttracker.data.repository.mapper;

import shipmenttracker.data.entity.provider.ClientProvider;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientProviderMapper implements RowMapper<ClientProvider> {
  @Override
  public ClientProvider mapRow(ResultSet rs, int rowNum) throws SQLException {
    ClientProvider newProvider = new ClientProvider();
    newProvider.setProviderName(rs.getString("provider_name"));
    newProvider.setToken(rs.getString("token"));
    return newProvider;
  }
}
