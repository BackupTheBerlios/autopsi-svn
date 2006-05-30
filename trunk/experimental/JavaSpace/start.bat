@rem starting http server
start java -jar D:\Programme\jini2_1\lib\classserver.jar -port 8080 -dir lib;D:\Programme\jini2_1\lib-dl

@rem Batch file to run SSL Reggie

start java -Djava.security.manager= ^
     -Djava.security.policy=D:\Programme\jini2_1\source\src\com\sun\jini\example\hello\config\ssl-reggie.policy ^
     -Djava.security.auth.login.config=D:\Programme\jini2_1\source\src\com\sun\jini\example\hello\config\ssl-reggie.login ^
     -Djava.security.properties=D:\Programme\jini2_1\source\src\com\sun\jini\example\hello\config\dynamic-policy.security-properties ^
     -Djavax.net.ssl.trustStore=D:\Programme\jini2_1\source\src\com\sun\jini\example\hello\config\prebuiltkeys\truststore ^
     -Djava.protocol.handler.pkgs=net.jini.url ^
     -jar D:\Programme\jini2_1\lib\start.jar ^
     D:\Programme\jini2_1\source\src\com\sun\jini\example\hello\config\start-ssl-reggie.config