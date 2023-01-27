cp /java/profile /etc/profile; \
source /etc/profile; \
sleep 5 && java -jar /java/Pet.jar & \
nginx -g "daemon off;"