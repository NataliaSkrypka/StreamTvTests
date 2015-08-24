Feature: For every user profile additional information as photo, and documents can be added

  Scenario: Add photo to profile
    Given delete all profiles for Sidorova+Nata+Anna sportsman
    Given login through API
    When add sportsman through API
      |lname    |dob       |region1|fst1|style|expires|fname|mname|lictype   |
      |Sidorova |20-12-2011|10     |3   |2    |2015   |Nata |Anna |1         |
    Given user login to streamtv site
    When user adds photo download2.jpg to Sidorova's profile
    Then photo is equal to expectedImage.png photo

    Scenario: Add attachment to profile
      Given delete all profiles for Sidorova+Nata+Anna sportsman
      Given login through API
      When add sportsman through API
        |lname    |dob       |region1|fst1|style|expires|fname|mname|lictype   |
        |Sidorova |20-12-2011|10     |3   |2    |2015   |Nata |Anna |1         |
      Given user login to streamtv site
      When user adds document TaskforTechnicalInterviewNIAutomationv0.2.pdf to Sidorova's profile
      Then document was added successfully

      Scenario: Delete attachment from profile
        Given user login to streamtv site
        When user delete attachment for Sidorova Nata Anna profile
        Then this sporsman's profile do not have attachments
