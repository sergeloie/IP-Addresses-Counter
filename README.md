# IP-Addresses-Counter

 [![PicoCLI](https://img.shields.io/badge/PicoCLI-4.7.5-green.svg)](https://github.com/remkop/picocli)
 [![Maintainability](https://api.codeclimate.com/v1/badges/ed3b9b81ba0273cb8e89/maintainability)](https://codeclimate.com/github/sergeloie/IP-Addresses-Counter/maintainability)
 [![Test Coverage](https://api.codeclimate.com/v1/badges/ed3b9b81ba0273cb8e89/test_coverage)](https://codeclimate.com/github/sergeloie/IP-Addresses-Counter/test_coverage)

Реализация тестового задания из [Списка тестовых заданий для прокачки](https://github.com/Hexlet/ru-test-assignments).  
а именно,  
[Посчитать количество уникальных IP-адресов в простом текстовом файле (Kotlin/Java)](https://github.com/Ecwid/new-job/blob/master/IP-Addr-Counter.md).

Реализация выполнена на Java с применением массива битов.  
Для учёта уникальных IP адресов заведены 2 битовых массива размерностью Integer.MAX_VALUE. Это сделано из-за ограничений на размер BitSet.  
Первый массив будет хранить IP адреса с первым октетом от 0 до 127, а второй - от 128 до 255.  
Из файла считывается строка с IP адресом и пребразуется в число типа long, это возможно, поскольку в IPv4 адрес представляет собой 32-битное число.  
Если число меньше Integer.MAX_VALUE, то оставляется, как есть. А если больше, то из него вычитается Integer.MAX_VALUE.  
После чего, на соответствующей этому числу позиции в первом или втором массиве битов соответственно устанавливается 1.  
После достижения конца файла подсчитывается и суммируется количество битов в значении 1 в обоих массивах и возвращается как ответ.  

Программа принимает в качестве параметра путь к файлу со списком IP адресов и ключ "-d", включающий отображение информации о номере считываемой строки и IP адресе в этой строке.  

```
./build/install/app-shadow/bin/app -d ./src/test/resources/small
```

Пример работы:  

[![asciicast](https://asciinema.org/a/xVd5egkriWnoasRwT9Mj2uahD.svg)](https://asciinema.org/a/xVd5egkriWnoasRwT9Mj2uahD)
