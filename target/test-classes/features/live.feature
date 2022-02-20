Feature: Life of a cell

  Scenario: Birth of a cell
      Given A dead cell has exactly three living neighbours
      When We go to the next step in time
      Then The cell becomes a living cell

  Scenario: Continuity of cell life
      Given A living cell has two or three living neighbours
      When We go to the next step in time
      Then The cell lives on
