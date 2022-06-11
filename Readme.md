# 스프링부트2로 웹서비스 출시하기

## CodeDeploy 에이전트 설치

EC2에 접속해서 다음 명령어를 입력합니다.

```shell
aws s3 cp s3://aws-codedeploy-ap-northeast-2/latest/install . --region ap-northeast-2
```

내려받기가 성공했다면 다음과 같은 메시지가 콘솔에 출력됩니다.

```shell
download: s3://aws-codedeploy-ap-northeast-2/latest/install to ./install
```

install 파일에 실행 권한이 없으니 실행 권한을 추가합니다.

```shell
chmod +x ./install
```

install 파일로 설치를 진행합니다.

```shell
sudo ./install auto
```

설치가 끝났으면 Agent가 정상적으로 실행되고 있는지 상태 검사를 합니다.

```shell
sudo service codedeploy-agent status
```

다음과 같이 running 메시지가 출력되면 정상입니다.

```shell
The AWS CodeDeploy agent is running as PID XXX
```

만약 설치 중에 다음과 같은 에러가 발생한다면 루비라는 언어가 설치 안 된 상태라서 설치를 합니다.

```shell
/usr/bin/env: ruby: No such file or diretory

# 이럴 경우 yum install 로 루비를 설치합니다.
sudo yum install ruby
```

## Travis CI, S3, CodeDeploy 연동

EC2 서버에 접속해서 다음과 같이 디렉토리를 생성합니다.

```shell
mkdir ~/app/step2 && mkdir ~/app/step2/zip
```

## 리눅스 명령어

```shell
# curl 은 remote 에서 받아온 데이터를 기본적으로는 콘솔에 출력합니다.
# -o 옵션 뒤에 FILE 을 적어주면 해당 FILE 로 저장합니다.
# -s 옵션은 진행 내역이나 메시지 등을 출력하지 않습니다. -o 옵션으로 /dev/null 를 작성하면 결과물도 출력되지 않습니다.
curl -s -o /dev/null -w "%{http_code}" http://localhost/profile

# list open files의 약자로 시스템에서 열려있는 파일에 대한 정보를 출력해주는 명령어입니다.
# -t 옵션을 주면 PID만 출력합니다.
# -i 옵션에 프로토콜 이름과 포트 번호를 명시해주면 특정 포트를 사용하는 프로세스 정보 출력합니다.
lsof -ti tcp:8080

# 하나의 문장을 만들어 파이프라인(l)으로 넘겨주기 위해 echo를 사용합니다.
# tee 명령어 : 화면에 출력되는것들을 파일로 출력
echo "set \$service_url http://127.0.0.1:${IDLE_PORT};" | sudo tee /etc/nginx/conf.d/service-url.inc 
```

## bash 스크립트 명령어

```shell
# 자바로 보면 일종의 import 구문입니다.
# 해당 코드로 인해 profile.sh의 여러 function을 사용할 수 있게 됩니다.
source profile.sh
```