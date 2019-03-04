@some-page 
Feature: Page Test 
	Login and check data on user account

@user-data 
Scenario Outline: Login as user and check data 
	Given I am on main page 
	When I type login <login> 
	And I type password <pass> 
	And I log in 
	Then I see my name <name> 
	And I see my city <city> 
	And I see message section 
	And I log out 
	
	Examples: 
		| login   | pass      | name   | city   |
		| user111 | mYP@ss    | John   | London |
		| user222 | notMYP@ss | Martin | Berlin |
		| user222 | mYP@ss    | Martin | Berlin |
