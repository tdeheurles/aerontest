# GCP

## cloud functions
```bash
gcloud functions deploy HelloHTTP --runtime go119 --trigger-http --allow-unauthenticated
```

## cloud run
```bash
docker run -v $(pwd)/components/gcp/.config:/root/.config -ti gcr.io/google.com/cloudsdktool/google-cloud-cli:latest bash
```
```bash
# see https://github.com/didil/gcp-api-gateway-demo#env

PROJECT_ID="aerontest"
REGION="europe-west9"
SERVICE_ACCOUNT="deployer"

gcloud auth login
gcloud iam service-accounts create "${SERVICE_ACCOUNT}" --project "${PROJECT_ID}"
gcloud iam service-accounts keys create tf/sa.json \
    --iam-account $SERVICE_ACCOUNT@$PROJECT_ID.iam.gserviceaccount.com \
    --project $PROJECT_ID
gcloud iam service-accounts keys create /root/data/sa.json \
    --iam-account $SERVICE_ACCOUNT@$PROJECT_ID.iam.gserviceaccount.com \
    --project $PROJECT_ID

gcloud services enable cloudresourcemanager.googleapis.com --project "${PROJECT_ID}"
gcloud services enable cloudbuild.googleapis.com --project "${PROJECT_ID}"
gcloud services enable apigateway.googleapis.com --project "${PROJECT_ID}"
gcloud services enable servicemanagement.googleapis.com --project "${PROJECT_ID}"
gcloud services enable servicecontrol.googleapis.com --project "${PROJECT_ID}"
```
