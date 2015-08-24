Feature: Sportsman can be added, edited, deleted through API

  Scenario: Add sportsman through API
    Given login through API
    When add sportsman through API
      | lname    | dob        | region1 | fst1 | style | expires | fname | mname | lictype |
      | Sidorova | 20-12-2011 | 10      | 3    | 2     | 2015    | Nata  | Anna  | 1       |
    Then sportsman was added successfully through API

  Scenario: Update sportsman data using API
    Given login through API
    Given search for Sidorova+Nata+Anna sportsman
    When update found sportsman through API :
      | lname   | dob        | region1 | fst1 | style | expires | fname | mname | lictype | card_state |
      | Petrova | 20-12-2012 | 11      | 2    | 1     | 2017    | Nata  | Anna  | 1       | 1          |
    Then sportsman was updated successfully through API

  Scenario: Delete sportsman data through API
    Given login through API
    Given search for Petrova+Nata+Anna sportsman
    When delete found sportsman
    Then there is no more sportman with Petrova+Nata+Anna fio