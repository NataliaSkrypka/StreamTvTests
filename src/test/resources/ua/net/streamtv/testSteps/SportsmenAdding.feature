Feature: Adding, editing, deleting sportsmen

  Scenario: Add new sportsman
    Given delete all profiles for Auto+Test+Anna sportsman
    Given user login to streamtv site
    When user adds new sportsman
      | Last Name | Date of Birth | Region    | Fst    | Style | Year | First Name | Middle Name | Region1   | Fst1  | Age    |
      | Auto      | 20-12-2011    | Poltavska | Dinamo | FS    | 2015 | Test       | Anna        | Poltavska | Kolos | Junior |
    Then he is added successfully

  Scenario: Edit sportsman
    Given user login to streamtv site
    When user change last name to Test for Auto Test Anna sportsman
    Then changes for Test Anna are made successfully
      | Last Name | Date of Birth | Region    | Fst    | Style | Year | First Name | Middle Name | Region1   | Fst1  | Age    |
      | Test      | 20-12-2011    | Poltavska | Dinamo | FS    | 2015 | Test       | Anna        | Poltavska | Kolos | Junior |

  Scenario: Delete sportsman
    Given user login to streamtv site
    When user delete Test Test Anna sportsman
    Then he is deleted successfully