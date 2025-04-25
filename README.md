**Welcome to My Ecommerce project for La lupita market. It is build using Angular 16, Spring Boot 3, Spring Security 6, Angular Material UI,  MySQL database along with Stripe for payment integration.**

Please watch this video for demo **https://www.youtube.com/watch?v=AOD99pfOclI**

**Please download the La Lupita demo and readme file if you want to see demo scrreen shots.** This is still at starting phase. I have deployed both angular and spring boot by creating docker image

**Customer Module:**
 Customers can securely log in, generate tokens, and access authenticated APIs through **JWT-based authentication and authorization** system with **role based Admin and Customer**.

All the products added by admin are visible in customer dashboard and they can see product details along with the customer rating and Frequently asked questions for those products.

Customer can click add the products to cart and they can increase the quantity of the product in cart as well.

Customer can apply discount's if they have any coupon and they can place order. Once the order is placed, it will take to the stripe payment page and if payment is successful, it will take us to payment successful page and you can view order details there.

Customer can share the review for the products and can upload image of the bought product on review as well.

There is favorite tab where customer can add the products to favorite.

Customer can update their profile details and upload their image as well.

They can track their order by using tracking ID.

**Admin Module:**
Admin can securely log in, generate tokens, and access authenticated APIs, using JWT-based authentication and authorization system.

Admin can create and update new categories for the products

Admin can create, update and delete products.
Admin can view all orders and change the order status to delivered/shipped 
analytics page will provide insights into placed, shipped, and in-progress orders. Additionally, view sales data for the current and previous months.

Admin can create Fnd view coupons along with Frequently asked questions.

