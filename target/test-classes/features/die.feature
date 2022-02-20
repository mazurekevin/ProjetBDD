Feature: Death of a cell

  Scenario: Underpopulation
      Given A living cell as less than two neighbours
      When We go to the next step in time
    Then The cell dies by underpopulation

  Scenario: Overcrowding
      Given A living cell as more than three neighbours
      When We go to the next step in time
      Then The cell dies by overcrowding
