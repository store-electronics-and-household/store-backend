FROM node:18 as builder
WORKDIR /app
RUN npm install -g typescript @typescript-eslint/parser@4.33.0
COPY package.json /app/package.json
RUN npm install
COPY . /app
RUN npm run build
FROM nginx:1.16.0-alpine
COPY nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=builder /app/build /usr/share/nginx/html
EXPOSE 3000
CMD ["nginx", "-g", "daemon off;"]