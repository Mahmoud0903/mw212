export {};
async function includeHTML(id: string, filePath: string): Promise<void> {
  const container = document.getElementById(id);
  if (!container) {
    console.warn(`Element mit ID "${id}" wurde nicht gefunden.`);
    return;
  }

  try {
    const response = await fetch(filePath);
    if (!response.ok) throw new Error(`Fehler beim Laden: ${filePath}`);
    const content = await response.text();
    container.innerHTML = content;
  } catch (error) {
    console.error(`Fehler beim Einfügen von ${filePath}:`, error);
  }
}

document.addEventListener('DOMContentLoaded', () => {
  includeHTML('header-placeholder', '../organisms/header.html');
  includeHTML('footer-placeholder', '../organisms/footer.html');
});
