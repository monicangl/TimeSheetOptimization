## People Query System

A simple restful API receive a http post request which contains a login name list in the request body, an optional order\_by parameter and an optional group_by parameter, and then returns the complete people information.


### Applicable scenario

When you have the login name of people, and want to get the complete people information.

### API definition/usage

#### Input:

| field | value | 
| :---: | :--- |
| method | POST |
| context path | /people |
| parameter | order\_by(optional, supported values: fullName, workingOffice), group\_by(optional, supported values: workingOffice)|
| headers | Content-Type = application/json | 
| request body | contain login name list in json format, for example: ["glnie", "bingwang"]| 

#### Output:
1. if order\_by and group\_by parameter both not specified, return requested people as a list ordered by full name, for example,
  
	```
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
	```

2. if only order\_by parameter specified, and value is "fullName", return requested people as a list, and ordered by the full name.
3. if only order\_by parameter specified, and value is "workingOffice", return requested people as a list ordered by the working office.
4. if only order\_by parameter specified, and is an unsupported value, return 400 and error message.
5. if only group_by parmeter sepcified, and value is "workingOffice", return requested people grouped by working office, and groups are ordered by working office, the people in a group ordered by full name, for example, if input login name list is: [
  "glnie",
  "bingwang",
  "azhu"
], the output is:

	```
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
		   "shortName": "glnie",
		  "fullName": "Guiling Nie"
		   }
	  	]
	  }
	]
	```

6. if only group_by parameter specified, but is unsupported value, return 400 and error message.
7. if  order\_by and group\_by parameter both specified, return 400 and error message.