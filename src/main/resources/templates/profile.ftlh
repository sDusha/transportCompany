<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Мой профиль</h1>
    <br>
    <div>
        Электронная почта: ${ user.getEmail()}
        Номер телефона: ${ user.getPhoneNumber()}
        <#if user.isAdmin()>
            <a href="/contracts">
                Заказы моего склада
            </a>
        <#else>
            <a href="/contracts">
                Мои заказы
            </a>
        </#if>
        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" style="text-align: center; width: 100%" class="btn btn-danger mb-2">
                Выйти из аккаунта
            </button>
        </form>
    </div>
</@t.template>