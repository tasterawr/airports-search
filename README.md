# airports-search

В решении не были использованы сторонние зависимости. На первом шаге, до запроса на ввод пользователем данных происходит полное чтение файла и запись в мапу
значения столбца в каждой строке (ключ) и соответственно номера данной строки (значение). После ввода подстроки пользователем, происходит поиск индексов строк, 
для которых значения столбцов начинаются соответствующей заданной подстрокой. 

При просмотре файла учитываются только полученные индексы строк, когда они просмотрены, заканчивается просмотр файла. При первом запросе программа у меня срабатывает дольше, при последующих скорость достаточно высокая. Ниже представлены некоторые мои запросы

Запрос "Bo"
![res1](https://user-images.githubusercontent.com/70659948/225335604-2103227a-3167-4fee-b3e0-6018d3af075c.JPG)
Запрос "Bo"
![res2](https://user-images.githubusercontent.com/70659948/225335678-90da4c6b-f628-41f6-902d-db85ee5c7833.JPG)
Запрос "Se"
![res3](https://user-images.githubusercontent.com/70659948/225335705-98102651-fdb4-4897-bc0c-3feaeef5ddae.JPG)
