Feature: ProductStore

  
  Scenario: Login into app
  
    Given User is on login Page
    When User INFO
    Then Home Page
  
   
  Scenario Outline: Add Item To Cart
    Given User is on home page
    When Add an Item "<category>" to "<item>" Cart
    Then Should Add items to the Cart
    Examples: 
      |category  |item        | 
      |Phones    |Nexus 6     | 
      |Laptops   |Sony vaio i5| 
   

   Scenario: Delete an Item  from Cart
  
    Given User is on cart the Cart Page
    When User deletes an Item
    Then Delete an item from cart
 
   Scenario: Purchase Items
  
    When  User clicks PlaceOrder
    Then  Order is Purchased
    

    
