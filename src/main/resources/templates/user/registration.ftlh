<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Регистрация</h1>
    <hr>
    <form action="/registration" method="post">
        <div class="form-group">
            <label for="login">Номер страхового полиса</label>
            <input type="login" class="form-control" required id="login" name="login">
        </div>
        <div class="form-group">
            <label for="password">Пароль</label>
            <input type="password" class="form-control" required id="password" name="password">
        </div>

       <div class="form-group">
            <label for="secondName">Фамилия</label>
            <input type="secondName" class="form-control" required id="secondName" name="secondName">
        </div>
        <div class="form-group">
            <label for="name">Имя</label>
            <input type="name" class="form-control" required id="name" name="name">
        </div>
       <div class="form-group">
            <label for="fatherName">Отчество</label>
            <input type="fatherName" class="form-control" required id="fatherName" name="fatherName">
        </div>
       <div class="form-group">
            <label for="dateOfBurn">Дата рождения</label>
            <input type="dateOfBurn" class="form-control" required id="dateOfBurn" name="dateOfBurn">
        </div>
       <div class="form-group">
            <label for="telephone">Телефонный номер</label>
            <input type="telephone" class="form-control" required id="telephone" name="telephone">
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" style="width: 100%" class="btn btn-dark">Зарегистрироваться</button>
    </form>
    <hr>
       <#if errorMessage??>
            <h2 style="color: red">${errorMessage}</h2>
        </#if>
</@t.template>