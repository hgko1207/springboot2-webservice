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