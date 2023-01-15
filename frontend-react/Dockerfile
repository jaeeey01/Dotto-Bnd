# 가져올 이미지를 정의
FROM node:14


# 실행 명령어
# 작업 공간
WORKDIR /app
COPY package*.json ./

RUN npm i
RUN npm run build

EXPOSE 3000
ENTRYPOINT ["node", "server.js"]
