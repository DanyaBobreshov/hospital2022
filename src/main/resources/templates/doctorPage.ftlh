<#import "blocks/template.ftlh" as t>
<@t.template user>
<doctor>
    <h1 style="text-align: center">Личная страница доктора</h1><hr>
    <h4 style="text-align: center">${doctor.speciality}</h4><hr>
    <h4 style="text-align: center">${doctor.user.secondName}</h4><hr>
    <h6 style="text-align: center">${doctor.cabinet}</h6><hr>

    <a href="/doctor-show-patients">Мои пациенты</a></h2><hr>
    <a href="/doctor-examination">Мои приемы</a></h2><hr>
    <a href="/doctor-appointments/${doctor.id}">Мои записи</a></h2><hr>
    <hr>
    <hr>
    <a href="/AddNewExaminationNull">Начать прием</a></h2><hr>
    </>
    </@t.template>
