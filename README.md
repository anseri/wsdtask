
## Development

When starting the application `docker compose up` is called and the app will connect to the contained services.

## Run the application

After starting the application it is accessible under `localhost:8085`.

## API 
please go to following link

	http://localhost:8085/swagger-ui/index.html

    API to return the wish list of a customer
        Endpoint: /api/customers/{customerId}/wishlist
        Method: GET
        Response: JSON representation of the customer's wish list.
		 Swagger Section: customer-resource
		 
		 
    API to return the total sale amount of the current day
        Endpoint: /api/sales/total
        Method: GET
        Response: JSON containing the total sale amount for the current day.
		Swagger Section: sales-resource
		
    API to return the max sale day of a certain time range
        Endpoint: /api/sales/max-day
        Method: GET
        Parameters: startDate (String), endDate (String)
        Response: JSON containing the date with the maximum sales within the given time range.
        Swagger Section: sales-resource

    API to return top 5 selling items of all time
        Endpoint: /api/items/top
        Method: GET
        Response: JSON containing the top 5 selling items of all 		time, ordered by total quantity sold.
        Swagger Section: sales-resource

    API to return top 5 selling items of the last month
        Endpoint: /api/items/top-last-month
        Method: GET
        Response: JSON containing the top 5 selling items of the last month, ordered by total quantity sold.

## Build

The application can be built using the following command:

```
gradlew clean build
```
```
gradlew bootBuildImage --imageName=com.wsd.ecommerce.task/wsd-task
```
