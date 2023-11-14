# IP-Addresses-Counter

 [![PicoCLI](https://img.shields.io/badge/PicoCLI-4.7.5-green.svg)](https://github.com/remkop/picocli)
 [![Maintainability](https://api.codeclimate.com/v1/badges/ed3b9b81ba0273cb8e89/maintainability)](https://codeclimate.com/github/sergeloie/IP-Addresses-Counter/maintainability)
 [![Test Coverage](https://api.codeclimate.com/v1/badges/ed3b9b81ba0273cb8e89/test_coverage)](https://codeclimate.com/github/sergeloie/IP-Addresses-Counter/test_coverage)
 [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=sergeloie_IP-Addresses-Counter&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=sergeloie_IP-Addresses-Counter)

Реализация тестового задания из [Списка тестовых заданий для прокачки](https://github.com/Hexlet/ru-test-assignments).  
а именно,  
[Посчитать количество уникальных IP-адресов в простом текстовом файле (Kotlin/Java)](https://github.com/Ecwid/new-job/blob/master/IP-Addr-Counter.md).

Реализация выполнена на Java с применением массива битов.  
Для учёта уникальных IP адресов заведены 4 битовых массива, собраные в список.
Каждый битовый массив будет хранить информацию о своём диапазоне IP адресов: 0.0.0.0 - 63.255.255.255, 64.0.0.0 - 127.255.255.255, 128.0.0.0 - 191.255.255.255 и 192.0.0.0 - 255.255.255.255.

### Алгоритм работы
Из файла считывается строка с IP адресом и пребразуется в число типа long, это возможно, поскольку в IPv4 адрес представляет собой 32-битное число.  
После чего вычисляется индекс массива в списке - целочисленным делением на $2^{30}$ и позиция бита в массиве - остатком от деления на $2^{30}$. Этот бит устанавливается в значение 1.  
После достижения конца файла подсчитывается и суммируется количество битов в значении 1 во всех массивах и возвращается как ответ.  

Программа принимает в качестве параметра путь к файлу со списком IP адресов и несколько ключей  
"-d", включающий отображение информации о номере считываемой строки и IP адресе в этой строке.  
"-v", включающий проверку, что строка является верным IP адресом  
"-m", для больших файлов, отображает информацию о каждой миллионной строке  

```
./build/install/app-shadow/bin/app -d ./src/test/resources/small
```

Пример работы:  

[![asciicast](https://asciinema.org/a/xVd5egkriWnoasRwT9Mj2uahD.svg)](https://asciinema.org/a/xVd5egkriWnoasRwT9Mj2uahD)



P.S. При решении задачи был написан класс IPSet, позволяющий хранить информацию о всех адресах в одной структуре данных, но такое решение сразу занимает 512 MB памяти, в отличие от BitSet, который увеличивается динамически и поэтому не используется.
