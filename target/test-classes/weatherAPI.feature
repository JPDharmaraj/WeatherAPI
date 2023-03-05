Feature: Validate API key, GetWeather, Register Stations

@valid
Scenario: Get Weather
	Given User hits the weather API URI
	When getWeather API finds valid API key and valid location
	Then getWeather API provides the weather details
	
@valid
Scenario: Register New Station
	Given User hits the registerStation API URI
	When API finds valid API key and valid post request
	Then registerStation API registers the station details and respond with unique ID
	
@valid @invalid
Scenario: Invalid API Key
	Given User hits the weather API URI without API key
	When getWeather API rejects the request
	And getWeather API returns authentication error
