#!/usr/bin/env bash
set -euo pipefail

name="<% name %>"
aws_region="<% aws_region %>"

buckets=$(aws s3api list-buckets | jq -r '.Buckets[].Name' )
bucket_exists="false"
for bucket in ${buckets}
do
  if [[ "${bucket}" == "${name}" ]]; then bucket_exists="true"; fi
done

if [[ "${bucket_exists}" == "false" ]];then
   aws s3api create-bucket \
    --bucket="${name}" \
    --acl="private" \
    --region="${aws_region}" \
    --create-bucket-configuration="LocationConstraint=${aws_region}"

    aws s3api put-public-access-block \
      --bucket="${name}" \
      --public-access-block-configuration="BlockPublicAcls=true,IgnorePublicAcls=true,BlockPublicPolicy=true,RestrictPublicBuckets=true"
fi
