cp /java/profile /etc/profile; \
source /etc/profile; \
java -jar /java/Gateway.jar & \
nginx -g "daemon off;"