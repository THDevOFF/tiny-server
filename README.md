<center><h1>Tiny Server</h1></center>
<center><h3>is a lightweight Paper plugin that starts a simple built-in HTTP server directly from your Minecraft server. Perfect for custom dashboards, status pages, resource hosting, and more — all from inside your plugin folder!</h3></center>
Available on:
<p>
-   <a href="https://modrinth.com/plugin/tiny-server">
    <img src="https://img.shields.io/badge/dynamic/json?color=158000&label=downloads&prefix=+%20&query=downloads&url=https://api.modrinth.com/v2/project/zfCCoCH5&logo=modrinth" alt="Modrinth Downloads">
  </a>
  <br>
-   <a href="https://www.spigotmc.org/resources/your-plugin-slug.00000/">
    <img src="https://img.shields.io/badge/SpigotMC-Download-orange?logo=spigotmc&style=flat" alt="SpigotMC">
  </a>
</p>
<hr>

`🔧 Features`


- 📡 Built-in HTTP server with no external dependencies

- 🌍 IP and port configurable via config.yml

- 📁 Serves static files from plugins/WebPlugin/web/

- 📄 Supports default index.html and custom file paths

- 🧠 Lightweight and easy to use — no NGINX or Apache required

- 🛠 Useful for status monitoring, mini dashboards, simple APIs, and more!

`🚀 Getting Started`

- Install the plugin on your Paper server.

- Configure ip and port in plugins/WebPlugin/config.yml.

- Place your static files (like index.html) into plugins/WebPlugin/web/.

- Access your content via http://your-server-ip:your-port.

`⚙️ Example config.yml`
```yml
ip: 0.0.0.0
port: 25984
```

`💡 Use Cases`


- Host a live server map or status page

- Provide downloadable resources to players

- Build a local web-based control panel
