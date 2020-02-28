Feature: Rest API automation with Java 8 Cucumber

  Scenario Outline: Service Name: contents ; Method Type: GET ;
    Given the GET rest service is up and running with the below parameters for the first service
      | URI       | Path   | InputParam   | HeaderParam   |
      | <apiHost> | <path> | <inputParam> | <headerParam> |
    When the GET rest service is up and running with the below parameters for the second service
      | URI       | Path   | InputParam   | HeaderParam   |
      | <apiHost> | <path> | <inputParam> | <headerParam> |
    Then compare the response for two service

    Examples: 
      | apiHost                               | path   | inputParam | headerParam                          |
      | https://jsonplaceholder.typicode.com/ | posts/ |          1 | { "Content-Type":"application/json"} |
      | https://jsonplaceholder.typicode.com/ | posts/ |          2 | { "Content-Type":"application/json"} |
      | https://jsonplaceholder.typicode.com/ | posts/ |          3 | { "Content-Type":"application/json"} |
