cp /java/profile /etc/profile; \
source /etc/profile; \
sleep 10 && java -jar /java/Animal.jar & \
nginx -g "daemon off;"