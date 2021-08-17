package shipmenttracker.data.exception;

public class ProviderAlreadyExistsException extends Exception {
  ProviderAlreadyExistsException(String providerName) {
    super("Could not create a provider with the name " + providerName + ", it already exists.");
  }
}
