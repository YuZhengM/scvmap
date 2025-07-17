@echo off
start cmd /c "scp -r D:\output\SCVdb\API\* root@bio.liclab.net:${project_path}/data/API/"
start cmd /c "scp -r D:\project\scvdb\target\SCVdb-1.0.0.war root@bio.liclab.net:${project_path}/data/code/service/scvdb_service.war"
