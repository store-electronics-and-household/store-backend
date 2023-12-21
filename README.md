# Интернет-магазин для продажи электроники и бытовой техники. Инструкция по настройке окружения на сервере бэкенда.

## Требования к окружению
* Docker версии 24.0.7 и выше
* Git версии 2.34.1 и выше

## Установка и настройка окружения

<details><summary>Установка и настройка Docker</summary>
  
В терминале на сервере ввести следующие команды:
* sudo apt install curl
* curl -fsSL https://get.docker.com -o get-docker.sh
* sh get-docker.sh
  
Внимание! Если при выполнении трех вышеперечисленных команд возникли ошибки, то 
поставить Docker в соответствии с рекомендациями, изложенными [здесь](https://docs.docker.com/engine/install/ubuntu/). 
Если все ОК, то продолжать вводить команды, приведенные ниже:

* sudo apt install \
  apt-transport-https \
  ca-certificates \
  curl \
  gnupg-agent \
  software-properties-common -y 
* curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
* sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
* sudo apt update
* sudo apt install docker-ce docker-compose -y
* Проверяем работоспособность Docker, для чего в терминале вводим команду
  sudo systemctl status docker
* Убеждаемся, что в выводе присутствуют строки примерно такого содержания:
  
  docker.service - Docker Application Container Engine
  
  Loaded: loaded (/lib/systemd/system/docker.service; enabled; vendor preset: enabled)
  
  Active: active (running) since Sun 2023-11-22 21:43:12 UTC; 13s ago
* Нажать Ctrl+C, затем ввести команду docker version. Версия Docker должна быть не менее 24.0.7.
</details>

<details><summary>Установка и настройка Git</summary>
  
В терминале на сервере ввести следующие команды:
* sudo apt-get install git
* git --version

Номер версии Git должен быть не менее 2.34.1.
</details>

http://45.12.236.120:8080/swagger-ui/index.html#/