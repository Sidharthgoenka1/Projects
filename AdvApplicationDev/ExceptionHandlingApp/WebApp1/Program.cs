using System.Net.Mime;
using Microsoft.AspNetCore.Diagnostics;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.AddRazorPages();

var app = builder.Build();


if (!app.Environment.IsDevelopment())
{
    
    app.UseExceptionHandler("/Error");
    // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
    app.UseHsts();
}
// app.UseStatusCodePages();

app.UseStatusCodePages(async statusCodeContext =>
{
    // using static System.Net.Mime.MediaTypeNames;
    statusCodeContext.HttpContext.Response.ContentType = "text/html";
    await statusCodeContext.HttpContext.Response.WriteAsync($"Status Code Page: {statusCodeContext.HttpContext.Response.StatusCode}");
});


// app.UseStatusCodePages(“text/html”, "Status Code Page: {0}");


// Configure the HTTP request pipeline. using lambda expression
// if (!app.Environment.IsDevelopment())
// {
//     app.UseExceptionHandler(exceptionHandlerApp =>
//         {
//         exceptionHandlerApp.Run(async context =>
//         {
//             context.Response.StatusCode = StatusCodes.Status500InternalServerError;
//            // using static System.Net.Mime.MediaTypeNames;
//             context.Response.ContentType= "text/html";//Text.Plain;
//             await context.Response.WriteAsync("An exception was thrown.");
//             var exceptionHandlerPathFeature =
//             context.Features.Get<IExceptionHandlerPathFeature>();
//             if (exceptionHandlerPathFeature?.Error is FileNotFoundException)
//             {
//                 await context.Response.WriteAsync(" The file was not found.");
//             }
//             if (exceptionHandlerPathFeature?.Path == "/")
//             {
//                 await context.Response.WriteAsync(" Page: Home.");
//             }
//         });
//     });
//     //app.UseExceptionHandler("/Error");
//     // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
//     app.UseHsts();
// }

app.UseHttpsRedirection();
app.UseStaticFiles();

app.UseRouting();

app.UseAuthorization();

app.MapRazorPages();

app.Run();
