<#import "blocks/template.ftlh" as t>
<@t.template user>
<doctor>
<date>
<time>
    <form action="/patient-appointment/${date}/${time}/${doctor.id}" method="post" enctype="multipart/form-data">
    <h4 style="text-align: center">${doctor.user.secondName}</h4><hr>
    <h4 style="text-align: center">${doctor.speciality}</h4><hr>
    <h4 style="text-align: center">${user.secondName}</h4><hr>
    <h4 style="text-align: center">${date}</h4><hr>
    <h4 style="text-align: center">${time}</h4><hr>

    <hr>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <button type="submit" style="width: 100%" class="btn btn-dark">Добавить</button>
            </form>
            <hr>
    </>
    </>
    </@t.template>