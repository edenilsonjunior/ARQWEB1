export const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));

export const loadData = async () => {
    try {
        const response = await fetch(`${contextPath}/load-data`);
        return await response.json();
    } catch (error) {
        console.error(error);
    }
};
