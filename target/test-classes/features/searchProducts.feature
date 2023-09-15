Feature: T-shirt price comparison on MercadoLibre

  As an online clothing store
  I want to gather information about t-shirt prices on MercadoLibre
  So that I can ensure my prices are competitive

  Scenario: Gather names, prices, and links of t-shirts on the first 3 pages of MercadoLibre
    Given the browser is open on the main page of MercadoLibre
    When I accept all the cookies
    And I enter "camisetas" into the search bar and press Enter
    When I navigate through the first 3 pages of results using the paginator and save info
    Then I save this information into a text file
