<#import "blocks/template.ftlh" as t>
<@t.template user>
<doctor>
    <h1 style="text-align: center">Личная страница доктора</h1><hr>
    <h4 style="text-align: center">${doctor.speciality}</h4><hr>
    <h4 style="text-align: center">${doctor.user.secondName}</h4><hr>
    <h6 style="text-align: center">${doctor.cabinet}</h6><hr>

    <a href="/doctorPage/patients/${doctor.id}">Мои пациенты</a></h2><hr>
    <a href="/doctorPage/examination/${doctor.id}">Мои приемы</a></h2><hr>
    <a href="/doctor-appointments/${doctor.id}">Мои записи</a></h2><hr>
    <hr>
    <hr>
    <a href="/doctorPage/AddNewExamination/${doctor.id}">Начать прием</a></h2><hr>
    </>
    </@t.template>
