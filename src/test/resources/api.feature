@apiTest   @apiGetTest
Feature: To test country iso details web-services

Scenario: get list of all countries
    Given user navigates to http://services.groupkt.com/country/get/all
    When retrieve the list of countries
    Then the status code is 200
    And response contains the following <tag> and <value>
    | tag                             | value |
    | RestResponse.result.alpha2_code | US    |
    | RestResponse.result.alpha2_code | DE    |
    | RestResponse.result.alpha2_code | GB    |

Scenario Outline: get details of specific iso code
    Given user calls country specific url http://services.groupkt.com/country/get/iso2code/ for <country>
    When retrieve the country details
    Then the response code is 200
    And response contains <value>
    Examples:
    | country | value                                                   |
    | US      | United States of America                                |
    | DE      | Germany                                                 |
    | GB      | United Kingdom of Great Britain and Northern Ireland    |

Scenario Outline: get details for an invalid iso country code
    Given user calls country specific url http://services.groupkt.com/country/get/iso2code/ for <country>
    When retrieve the country details
    Then the response code is 200
    And response contains <value>
    Examples:
    | country | value                                                   |
    | XX      | Invalid                                                 |