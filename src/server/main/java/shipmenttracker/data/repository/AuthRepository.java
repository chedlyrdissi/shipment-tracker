package shipmenttracker.data.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import shipmenttracker.data.entity.credentials.AuthCredentials;
import shipmenttracker.data.entity.credentials.BasicAuthCredentials;
import shipmenttracker.data.entity.credentials.BearerAuthCredentials;
import shipmenttracker.data.entity.provider.ClientProvider;
import shipmenttracker.data.entity.provider.Provider;
import shipmenttracker.data.repository.mapper.ClientProviderMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AuthRepository {

  private final static String INSERT_SQL = "WITH newsalt AS (SELECT gen_salt('bf', 8) AS salt)\n" +
    "INSERT INTO provider(provider_name, password, salt)" +
    " VALUES (:providerName, crypt(:password, (SELECT salt from newsalt)), (SELECT salt from newsalt));";

  private final static String BASIC_AUTH_SQL =
    "SELECT provider_name, token\n" +
    "FROM provider\n" +
    "WHERE provider_name = :providerName\n" +
    "AND password = crypt(:password, salt);";

  private final static String TOKEN_AUTH_SQL = "SELECT provider_name, token\n" +
    "FROM provider\n" +
    "WHERE token = :token;";

  @Autowired
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  // TODO remove sql from error
  public ClientProvider createProvider(Provider provider) {
    KeyHolder holder = new GeneratedKeyHolder();
    SqlParameterSource parameters = new MapSqlParameterSource()
      .addValue("providerName", provider.getProviderName())
      .addValue("password", provider.getPassword());
    namedParameterJdbcTemplate.update(INSERT_SQL, parameters, holder);
    ClientProvider newProvider = new ClientProvider();
    newProvider.setProviderName(provider.getProviderName());
    newProvider.setToken((String) holder.getKeys().get("token"));
    return newProvider;
  }

  public ClientProvider authenticate(String credentials) throws Exception {
    AuthCredentials translatedCredentials = AuthCredentials.translateCredentials(credentials);
    Map<String, String> parameters = new HashMap<>();
    String queryString;
    if(translatedCredentials.isBasicCredentials()) {
      queryString = BASIC_AUTH_SQL;
      String providerName = ((BasicAuthCredentials) translatedCredentials).getUsername();
      String password = ((BasicAuthCredentials) translatedCredentials).getPassword();
      parameters.put("providerName", providerName);
      parameters.put("password", password);
    } else if (translatedCredentials.isBearerCredentials()) {
      queryString = TOKEN_AUTH_SQL;
      String token = ((BearerAuthCredentials) translatedCredentials).getToken();
      parameters.put("token", token);
    } else {
      throw new Exception("This is not supposed to happen");
    }
    List<ClientProvider> clientProviders = namedParameterJdbcTemplate.query(queryString, parameters, new ClientProviderMapper());
    if(clientProviders.size() == 0) {
      throw new Exception("Invalid Credentials");
    }
    if(clientProviders.size() > 1) {
      throw new Exception("duplicate providers exception");
    }
    return clientProviders.get(0);
  }
}
