cd MovieCruisorAuthenticationService
source ./env-variable.sh
mvn clean package
docker build -t user-service .
cd ..
cd MovieCruisorBackend
source ./env-variable.sh
mvn clean package
docker build -t movie-service .
cd ..

