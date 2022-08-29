<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Мои записи</h1><hr>
     <table class="table table-hover table-dark">
                <tr>
                    <th scope="col">id</th>
                    <th scope="col">Дата</th>
                    <th scope="col">Время</th>
                    <th scope="col">Кабинет</th>
                    <th scope="col">Доктор</th>
                    <th scope="col">Специальность</th>
                </tr>
                <#list appointments as appointment>
                <tr>
                    <th>${appointment.id}</th>
                    <th>${appointment.date}</th>
                    <th>${appointment.time}</th>
                    <th>${appointment.cabinet}</th>
                    <th>${appointment.doctor.user.secondName}</th>
                    <th>${appointment.doctor.speciality}</th>
                    <th><a href="/appointment-delete/${appointment.id}">Удаление</a></th>
                </tr>
                <#else>
                <h3>Записей нет</h3>
            </#list>
            </table>
    <form method="get" action="/patient-appointments">
            <button style="width: 100%" type="submit" class="btn btn-dark">Загрузить</button>
    </form>
    <hr>
 <hr>
 </@t.template>