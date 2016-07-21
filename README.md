## People Query System

A simple restful API receive a http post request which contains a login name list in the request body, an optional order__by_ parameter and an optional group_by parameter, and then return the complete people information.


#### **Applicable scenario**

When you have the login name of people, and want to get the complete people information.

### API definition/usage

#### Input:

**method**: POST

**context path**: /People

**parameter**: order__by_(optional) and group_by(optional).

order_by supported values: [fullName, workingOffice]

group_by supported values: [workingOffice]

**headers**: Content-Type = application/json

**request body**: contain login name list in json format, for example:
[
  "glnie",
  "bingwang"
]

#### Output:
* if order__by_ and group_by parameter both not specified, return requested people as a list, and ordered by the full name, such as: 


	[
	
	
	  {
	
	   "shortName": "bingwang",
	
	
	   "fullName": "Bing Wang",
	
	   "workingOffice": "Chengdu"
	
	
	  },
	
	
	  {
	
	
	   "shortName": "glnie",
	
	
	   "fullName": "Guiling Nie",
	
	
	   "workingOffice": "Chengdu"
	
	
	  }
	
	
	]


* if only order__by_ parameter specified, and value is "fullName", return requested people as a list, and ordered by the full name.
* if only order__by_ parameter specified, and value is "workingOffice", return requested people as a list, and ordered by the working office.
* if only order_by parameter specified, but is a unsupported value, return 400 and error message.
* if only group_by parmeter sepcified, and value is "workingOffice", return requested people grouped by working office, groups ordered by working office, and the people in a group ordered by full name, such as, if input login name list is: [
  "glnie",
  "bingwang",
  "azhu"
], the output is:

	[
	
	  {
	  
	   "workingOffice": "Beijing",
	
	   "peopleCount": 1,
	
	
	   "people": [
	
	
	   {
	
	
	   "shortName": "azhu",
	
	
	   "fullName": "Ao Zhu"
	
	
	   }
	
	
	 ]
	
	
	  },
	
	
	  {
	
	
	  "workingOffice": "Chengdu",
	
	
	  "peopleCount": 2,
	
	
	  "people": [
	
	
	  {
	
	
	   "shortName": "bingwang",
	
	
	  "fullName": "Bing Wang"
	
	
	   },
	
	
	   {
	
	
	   "shortName": "glnie"
	
	  "fullName": "Guiling Nie"
	  
	   }
	   
	  ]
	  
	 },
	
	]

* if only group_by parameter specified, but is unsupported value, return 400 and error message.
* if  order__by_ and group_by parameter both specified, return 400 and error message.