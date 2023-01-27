cp /java/profile /etc/profile; \
source /etc/profile; \
java -jar /java/Gateway.jar & \
sleep 5 && java -jar /java/Pet.jar & \
sleep 10 && java -jar /java/Animal.jar & \
nginx -g "daemon off;"