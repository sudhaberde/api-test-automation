@apiTest @apiPostTest
Feature: Test country code post api

Scenario: add a country
    Given user calls post url http://services.groupkt.com/country/post/ for XX
    When post the country details
    Then the post response code is 200