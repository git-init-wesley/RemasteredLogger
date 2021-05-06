[Started]: https://img.shields.io/static/v1?label=Started&message=04-25-2021&style=for-the-badge&color=blue

[Licence]: https://img.shields.io/static/v1?label=Licence&message=Apache-2.0%20License&style=for-the-badge&color=lightgrey

[Javadoc]: https://img.shields.io/static/v1?label=Javadoc&message=View&style=for-the-badge&color=C14DFF

[Wiki]: https://img.shields.io/static/v1?label=Wiki&message=View&style=for-the-badge&color=FF4D4D

[Versioning]: https://img.shields.io/static/v1?label=Versioning&message=View&style=for-the-badge&color=ff69b4

[Release]: https://img.shields.io/static/v1?label=Release&message=v1.1.2&style=for-the-badge&color=red

[Version]: https://img.shields.io/static/v1?label=Version&message=1.1.2&style=for-the-badge&color=light_green

[SecurityCheck]: https://img.shields.io/static/v1?label=Security&message=&check;&style=for-the-badge&color=light_green

[SecurityUncheck]: https://img.shields.io/static/v1?label=Security&message=&cross;&style=for-the-badge&color=red

[LoggerVersion]: https://img.shields.io/static/v1?label=Version&message=1.1.2&style=for-the-badge&color=light_green

[ILoggerVersion]: https://img.shields.io/static/v1?label=Version&message=1.1.2&style=for-the-badge&color=light_green

[LoggerFactoryVersion]: https://img.shields.io/static/v1?label=Version&message=1.1.2&style=for-the-badge&color=light_green

[ILoggerFactoryVersion]: https://img.shields.io/static/v1?label=Version&message=1.1.2&style=for-the-badge&color=light_green

# RemasteredLogger

RemasteredLogger is a logger as simple as it is complex, it uses SLF4j as an API base. This logger provides the
developer with ease of use for a wide variety of utility. This also allows the simplicity of saving under several
loggers but also the complexity of being able to create “Class” or single event loggers.


> ![Started][]
>
> [ ![Licence][] ](https://github.com/Etsuko-Network/API-Java/blob/main/LICENCE)
>
> [ ![Javadoc][] ](https://kanekireal.github.io/RemasteredLogger/)
>
> [ ![Wiki][] ](https://github.com/kanekireal/RemasteredLogger/wiki)
>
> [ ![Versioning][] ](http://semver.org/)
>
>  [ ![Release][] ](https://github.com/kanekireal/RemasteredLogger/releases)
>
> ![Version][]

### Class Version

| Class | Version | Security |
| ------ | ------ | ------ |
| Logger | ![LoggerVersion][] | ![SecurityCheck][] |
| ILogger | ![ILoggerVersion][] | ![SecurityCheck][] |
| LoggerFactory | ![LoggerFactoryVersion][] | ![SecurityCheck][] |
| ILoggerFactory | ![ILoggerFactoryVersion][] | ![SecurityCheck][] |

## Use

```groovy
// https://mvnrepository.com/artifact/org.jetbrains/annotations
implementation group: 'codes.wesley-dev', name: 'remasteredlogger', version: '1.1.2'
```

## Dependencies

> Jetbrains Annotations
> 
> SLF4j

```groovy
// https://mvnrepository.com/artifact/org.slf4j/slf4j-api
implementation group: 'org.slf4j', name: 'slf4j-api', version: '2.0.0-alpha1'
// https://mvnrepository.com/artifact/org.jetbrains/annotations
implementation group: 'org.jetbrains', name: 'annotations', version: '20.1.0'
```

## Auteurs

> **Wesley Levasseur** — *Creator, Initial Work* - [KanekiReal](https://github.com/kanekireal)
