rem mvn clean verify 
rem mvn clean verify -DtestName=**/*PackageManageChangeOwnerStoryTest.java
rar a -r -ag  d:\testresults\testresults .\target\site test-activity.log
rem gdrive.exe upload --file D:\site --parent 0B3CKItL1h_8TdXdUclZqampKYkk