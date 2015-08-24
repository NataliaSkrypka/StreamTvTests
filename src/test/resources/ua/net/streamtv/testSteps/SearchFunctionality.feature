Feature: Search functionality

  Scenario: Test search parameters
    Given delete all profiles for Ivanova+Elena+Anna sportsman
    Given login through API
    When add sportsman through API
      |lname   |dob       |region1|fst1|style|expires|fname|mname|lictype   |
      |Ivanova |20-12-2011|10     |3   |2    |2015   |Elena|Anna |1         |
    Given user login to streamtv site
    When user search for Ivanova with Ivano-Frankivska region and Kolos fst
    Then only one record for uploaded sportsman is shown