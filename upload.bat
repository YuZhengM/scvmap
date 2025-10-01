@echo off
start cmd /c "scp -r D:\project\scvdb\target\scVMAP-1.0.0.war ${username}@${IP}:${project_path}/data/code/service/scvmap_service.war"
