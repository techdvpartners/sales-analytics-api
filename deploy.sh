./gradlew build
gcloud compute scp build/libs/* instance-1:/home/mohak/sf-pricing-framework/
gcloud compute ssh instance-1
java -jar /home/mohak/sf-pricing-framework/sales-analytics-api-0.0.1.jar &