# api-test-automation
api-test-automation through cucumber

The project accomplishes below tasks:
Given  are the  following web-services:
http://services.groupkt.com/country/get/all
http://services.groupkt.com/country/get/iso2code/{COUNTRY_ISO2CODE} (e.g. http://services.groupkt.com/country/get/iso2code/us )

Using Cucumber to describe the steps and Rest Assured to executed the Requests, create following automated test scenarios:
1.Get all countries and validate that US, DE and GB were returned in the response
2. Get each country (US, DE and GB) individually and validate the response.Try to get information for in-existent countries and validate the response
3. Write a test that would validate new country addition using POST(it will not work now, but no worries).
Example of json body for POST is below:
{

name: "Test Country",

alpha2_code: "TC",

alpha3_code: "TCY"

}



Run using `mvn clean test -Dcucumber.options="src/test/resources --tags @apiTest"`
