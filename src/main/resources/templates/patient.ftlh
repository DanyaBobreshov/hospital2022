<#import "blocks/template.ftlh" as t>
<@t.template user>
<patient>
    <h1 style="text-align: center">Личная страница пациента</h1><hr>
    <h4 style="text-align: center">${patient.user.secondName}</h4><hr>
    <h4 style="text-align: center">${patient.user.name}</h4><hr>
    <h4 style="text-align: center">${patient.user.fatherName}</h4><hr>
    <h4 style="text-align: center">${patient.user.dateOfBurn}</h4><hr>
    <h4 style="text-align: center">${patient.user.telephone}</h4><hr>
    <h4 style="text-align: center">${patient.user.login}</h4><hr>

    <a href="/patient-appointments">Мои записи</a></h2><hr>
    <a href="/patient-diseases">Мои Заболевания</a></h2><hr>
    <hr>
    <hr>
    <a href="/patient-searchDoctor">Новая запись</a></h2><hr>
    </>
    </@t.template>
