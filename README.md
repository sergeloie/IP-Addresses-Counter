# IP-Addresses-Counter

 [![PicoCLI](https://img.shields.io/badge/PicoCLI-4.7.5-green.svg)](https://github.com/remkop/picocli)
 [![Maintainability](https://api.codeclimate.com/v1/badges/ed3b9b81ba0273cb8e89/maintainability)](https://codeclimate.com/github/sergeloie/IP-Addresses-Counter/maintainability)
 [![Test Coverage](https://api.codeclimate.com/v1/badges/ed3b9b81ba0273cb8e89/test_coverage)](https://codeclimate.com/github/sergeloie/IP-Addresses-Counter/test_coverage)
 [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=sergeloie_IP-Addresses-Counter&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=sergeloie_IP-Addresses-Counter)

Реализация тестового задания из [Списка тестовых заданий для прокачки](https://github.com/Hexlet/ru-test-assignments).  
а именно,  
[Посчитать количество уникальных IP-адресов в простом текстовом файле (Kotlin/Java)](https://github.com/Ecwid/new-job/blob/master/IP-Addr-Counter.md).

Реализация выполнена на Java с применением массива битов.  
Для учёта уникальных IP адресов заведены 4 битовых массива, собранные в список.
Каждый битовый массив будет хранить информацию о своём диапазоне IP адресов: 0.0.0.0 - 63.255.255.255, 64.0.0.0 - 127.255.255.255, 128.0.0.0 - 191.255.255.255 и 192.0.0.0 - 255.255.255.255.

### Алгоритм работы
Из файла считывается строка с IP адресом и преобразуется в число типа long, это возможно, поскольку в IPv4 адрес представляет собой 32-битное число.  
После чего вычисляется индекс массива в списке - целочисленным делением на $2^{30}$ и позиция бита в массиве - остатком от деления на $2^{30}$. Этот бит устанавливается в значение 1.  
После достижения конца файла подсчитывается и суммируется количество битов в значении 1 во всех массивах и возвращается как ответ.  

Программа принимает в качестве параметра путь к файлу со списком IP адресов и несколько ключей  
"-d", включающий отображение информации о номере считываемой строки и IP адресе в этой строке.  
"-m", для больших файлов, отображает информацию о каждой миллионной строке  

```shell
./app/build/install/app/bin/app -d ./app/src/test/resources/small
```



P.S. При решении задачи был написан класс IPSet, позволяющий хранить информацию о всех адресах в одной структуре данных, но такое решение сразу занимает 512 MB памяти, в отличие от BitSet, который увеличивается динамически, и не даёт прироста производительности, поэтому не используется.

В процессе решения были испробованы и отвергнуты следующие подходы:

#### Префиксные деревья
Огромный расход памяти - 160 миллионов адресов заняло 12 ГБ ОЗУ, после чего выполнение программы практически остановилось из-за нехватки памяти.  
<br />
  
#### Использование регулярных выражений для проверки корректности IP адреса
Значительно замедление скорости работы - порядка 5 часов на тестовый файл.

Например, для метода isIPv4Address() скорость выполнения ≈ 700,000 операций в секунду. И 2,700,000, если вынести вычисление regexPattern из метода
```java
    public static boolean isIPv4Address(String ipAddress) {
        String pattern = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        Pattern regexPattern = Pattern.compile(pattern);
        return regexPattern.matcher(ipAddress).matches();
    }
```

| Benchmark                          | Mode  | Cnt |       Score |       Error | Units |
|------------------------------------|-------|-----|------------:|------------:|-------|
| MatchBench.isIPv4AddressInnerRegex | thrpt | 25  |  715006,184 |  ± 5881,340 | ops/s |
| MatchBench.isIPv4AddressOuterRegex | thrpt | 25  | 2679271,358 | ± 92405,723 | ops/s |

<br />

#### Разбиение IP адреса на октеты и преобразование строки в число

Сравнение метода ipToLong() и текущего метода для преобразования строки с IP адресом в число
```java
    public static long ipToLong(String ipAddress) {
        String[] octetStrings = ipAddress.split("\\.");
        long result = 0;

        for (int i = 0; i < 4; i++) {
            long octetValue = Long.parseLong(octetStrings[i]);
            result += octetValue << (8 * (3 - i));
        }
        return result;
    }
```

| Benchmark                              | Mode  | Cnt |        Score |        Error | Units |
|----------------------------------------|-------|-----|-------------:|-------------:|-------|
| IPtoLongBench.testStringSplitToIP      | thrpt | 25  |  6480849,205 | ± 177321,560 | ops/s |
| IPtoLongBench.testStringCharByCharToIP | thrpt | 25  | 60621167,508 | ± 437508,189 | ops/s |

---

В итоге, сейчас используется метод посимвольного преобразования строки в IP адрес, подсмотренный в OpenJDK.
Помимо преобразования в методе одновременно осуществляется проверка на корректность адреса.
Скорость выполнения зависит от количества знаков.

| Benchmark                 | Mode  | Cnt |         Score |         Error | Units |
|---------------------------|-------|-----|--------------:|--------------:|-------|
| IPLengthBench.test4Digit  | thrpt | 25  | 123210472,781 |  ± 272082,957 | ops/s |
| IPLengthBench.test8Digit  | thrpt | 25  |  82186672,406 |  ± 545401,768 | ops/s |
| IPLengthBench.test12Digit | thrpt | 25  |  59494004,545 | ± 2726267,979 | ops/s |

<br />

Запуск для тестового файла в 106 ГБ, 8 миллиардов строк, показал следующие результаты:  

Для SSD - 00:12:03.019

Для HDD - 00:19:34.167  

---

Для запуска приложения необходимо склонировать репозиторий
```shell
gh repo clone sergeloie/IP-Addresses-Counter
```
  
Перейти в каталог с приложением и скомпилировать его
```shell
cd IP-Addresses-Counter && make run-dist
```
  
Запустить, указав параметры и путь к файлу
```shell
./app/build/install/app/bin/app -d ./app/src/test/resources/small
```